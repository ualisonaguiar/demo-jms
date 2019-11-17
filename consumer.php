<?php
/**
 * Created by IntelliJ IDEA.
 * User: ualison
 * Date: 16/11/19
 * Time: 23:57
 */
require_once __DIR__ . '/vendor/autoload.php';
require_once 'SendRabbitMQ.php';

use Symfony\Component\Yaml\Yaml;
use PhpAmqpLib\Connection\AMQPStreamConnection;

$arrCongiYaml = Yaml::parse(file_get_contents('application.yml'));
$arrRabbit = $arrCongiYaml['rabbitmq'];
$arrQueue = $arrCongiYaml['queue'];

$connection = new AMQPStreamConnection(
    $arrRabbit['host'],
    $arrRabbit['port'],
    $arrRabbit['username'],
    $arrRabbit['password']
);
$channel = $connection->channel();
$channel->queue_declare($arrQueue['processar'], true, true);

$channel->basic_consume($arrQueue['processar'], '', false, true, false, false, function ($strMessage) {
    $strInfoCEP = consultaCEP($strMessage->body);
    if (empty($strInfoCEP)) {
        enviaFilaFalha($strMessage->body);
    } else {
        enviaFilaCarga($strInfoCEP);
    }
});

while (count($channel->callbacks)) {
    $channel->wait();
}

$channel->close();
$channel->close();

function enviaFilaFalha($strMessage)
{
    $arrCongiYaml = Yaml::parse(file_get_contents('application.yml'));
    $sendRabbitMQ = new SendRabbitMQ();
    $sendRabbitMQ->send($arrCongiYaml['queue']['falha'], $strMessage);
}

function enviaFilaCarga($strMessage)
{
    $arrCongiYaml = Yaml::parse(file_get_contents('application.yml'));
    $sendRabbitMQ = new SendRabbitMQ();
    $sendRabbitMQ->send($arrCongiYaml['queue']['carga'], $strMessage);
}

function consultaCEP($strCEP)
{
    $arrCongiYaml = Yaml::parse(file_get_contents('application.yml'));
    $strUrl = $arrCongiYaml['webservice']['correio'] . '/' . $strCEP . '/json';

    $curl = curl_init();
    $arrOptionsCurl = [
        CURLOPT_URL => $strUrl,
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => '',
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 30,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => 'GET',
        CURLOPT_FOLLOWLOCATION => true,
        CURLOPT_HTTPHEADER => [
            "cache-control: no-cache",
            "content-type: application/json",
        ]
    ];
    curl_setopt_array($curl, $arrOptionsCurl);
    $mixResponse = curl_exec($curl);
    $arrInfoCurl = curl_getinfo($curl);
    curl_close($curl);
    if ($arrInfoCurl['http_code'] != 200) {
        return '';
    }
    return $mixResponse;
}