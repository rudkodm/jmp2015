Please create CLI application which scans a specified folder and provides detailed statistics:
1. File count
2. Folder count
3. Size (sum of all files size)
(Similar like windows context menu "properties")
Since the folder may contain huge amount of files the scanning process should be executed in a separate thread displaing an informational message with some simple annimation like progress bar in CLI (up to you, but I'd like to see that task is in porgress). Once task is done the statistics should be displayed in the output immediately. Additionally there should be ability to interrut the process pressing some reserved key (for instance "c").
Note: it's allowed to add some sleeps between file interations to simulate very long progress.

ок.
[6/2/2015 1:08:10 PM] Vitali Smaliarou: тогда еще один общий таск:
есть список ссылок на файлы (архивы) нужно написать апликуху которая скачивала их параллельно, кол-во потоков задается в константе или через ввод с консоли
[6/2/2015 1:09:21 PM] Vitali Smaliarou: после завершения скачивания всех файлов, вывести содержания фолдера (куда была скачка)
[6/2/2015 1:09:41 PM] Vitali Smaliarou: предусмотреть таймаут на скачивание
[6/2/2015 1:10:19 PM] Vitali Smaliarou: т.е. скипать скачивание, если не успели скачать за 30 секунд например (таймаут или вводится или берется из константы)
[6/2/2015 1:10:26 PM] Vitali Smaliarou: понятен таск ?