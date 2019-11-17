<?php
require_once __DIR__ . '/vendor/autoload.php';

use Symfony\Component\Yaml\Yaml;
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

class SendRabbitMQ
{

    public function send($strQueue, $strMessage)
    {
        $channel = $this->getConection();
        $channel->queue_declare($strQueue, true, true);
        $channel->basic_publish(new AMQPMessage($strMessage), '', $strQueue);

        $channel->close();
        $channel->close();
    }

    private function getConection()
    {
        $arrCongiYaml = Yaml::parse(file_get_contents('application.yml'));
        $arrRabbit = $arrCongiYaml['rabbitmq'];

        $connection = new AMQPStreamConnection(
            $arrRabbit['host'],
            $arrRabbit['port'],
            $arrRabbit['username'],
            $arrRabbit['password']
        );
        return $connection->channel();
    }


}