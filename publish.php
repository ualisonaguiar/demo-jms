<?php
/**
 * Created by IntelliJ IDEA.
 * User: ualison
 * Date: 16/11/19
 * Time: 23:26
 */
require_once __DIR__ . '/vendor/autoload.php';

use Symfony\Component\Yaml\Yaml;
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;


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
$channel->queue_declare($arrQueue['recebe'], true, true);
$channel->basic_publish(new AMQPMessage('01011100'), '', $arrQueue['recebe']);

$channel->close();
$channel->close();