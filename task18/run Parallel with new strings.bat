java -cp bin\ -Xmx2g -Xloggc:logs\ParallelGC_newStr.log -XX:+PrintGCDetails -XX:+UseParallelGC com.jmp17.gc.run.Main -useNewStrings