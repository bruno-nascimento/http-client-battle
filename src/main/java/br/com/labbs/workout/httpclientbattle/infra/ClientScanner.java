package br.com.labbs.workout.httpclientbattle.infra;

import br.com.labbs.workout.httpclientbattle.shared.HttpClient;
import io.github.lukehutch.fastclasspathscanner.ClassInfoList;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.ScanResult;

public class ClientScanner {

    private ClientScanner() {
    }

    public static HttpClient get() throws IllegalAccessException, InstantiationException {
        final ScanResult scanResult = new FastClasspathScanner().enableAllInfo().scan();
        ClassInfoList classesImplementing = scanResult.getClassesImplementing(HttpClient.class.getName());
        return classesImplementing.get(0).loadClass(HttpClient.class).newInstance();
    }

}
