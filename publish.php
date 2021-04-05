<?php
/**
 * Created by IntelliJ IDEA.
 * User: ualison
 * Date: 16/11/19
 * Time: 23:26
 */
require_once __DIR__ . '/vendor/autoload.php';
require_once 'SendRabbitMQ.php';

use Symfony\Component\Yaml\Yaml;

$arrCongiYaml = Yaml::parse(file_get_contents('application.yml'));

$sendRabbitMQ = new SendRabbitMQ();
$sendRabbitMQ->send($arrCongiYaml['queue']['recebe'], '0101110');
$sendRabbitMQ->send($arrCongiYaml['queue']['recebe'], '0101110');
$sendRabbitMQ->send($arrCongiYaml['queue']['recebe'], '0101110');
$sendRabbitMQ->send($arrCongiYaml['queue']['recebe'], '0101110');
