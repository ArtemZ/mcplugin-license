<?
error_reporting(0);
$before = microtime(true);

for ($i=0 ; $i<100000 ; $i++) {
    serialize($list);
}

$after = microtime(true);

echo $end4 - $start4/10 . " sec/serialize\n";
?>
