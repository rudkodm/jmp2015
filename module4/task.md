Please create CLI application which scans a specified folder and provides detailed statistics:
1. File count
2. Folder count
3. Size (sum of all files size)
(Similar like windows context menu "properties")
Since the folder may contain huge amount of files the scanning process should be executed in a separate thread
displaying an informational message with some simple animation like progress bar in CLI (up to you, but I'd like
to see that task is in progress). Once task is done the statistics should be displayed in the output immediately.
Additionally there should be ability to interrupt the process pressing some reserved key (for instance "c").
Note: it's allowed to add some sleeps between file interruptions to simulate very long progress.


Есть список ссылок на файлы (архивы). нужно написать апликуху которая: 
- скачивала их параллельно;
- кол-во потоков задается в константе или через ввод с консоли;
- после завершения скачивания всех файлов, вывести содержания фолдера (куда была скачка)
- предусмотреть таймаут на скачивание
т.е. скипать скачивание, если не успели скачать за 30 секунд например (таймаут или вводится или берется из константы)