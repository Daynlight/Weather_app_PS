package kafka.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Scheduler {

    private final ScheduledExecutorService service;
    private final long period = 5;//Repeat interval
    public Scheduler()
    {
        service = Executors.newScheduledThreadPool(1);
    }

    public void startScheduler(Runnable runnable)
    {
        final ScheduledFuture<?> handler = service.scheduleAtFixedRate(runnable,0,period, TimeUnit.MINUTES);//Will cause the task to execute after every 30 seconds

    }


}
