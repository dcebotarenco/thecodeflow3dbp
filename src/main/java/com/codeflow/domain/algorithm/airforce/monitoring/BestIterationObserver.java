package com.codeflow.domain.algorithm.airforce.monitoring;

import com.codeflow.domain.iteration.Iteration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Collections.reverseOrder;

public class BestIterationObserver implements Observer {

    public static final Logger LOGGER = LoggerFactory.getLogger(BestIterationObserver.class);
    private static ExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private final List<Iteration> iterations;
    private final FutureTask<Iteration> determineBestIterationTask;
    private final AtomicBoolean bestIterationFoundBeforeAllEnd = new AtomicBoolean(false);
    private final AtomicBoolean allIterationsFinished = new AtomicBoolean(false);
    private final AtomicBoolean wait = new AtomicBoolean(true);


    public BestIterationObserver(List<Iteration> iterations) {
        this.iterations = iterations;
//        this.iterations.forEach(i -> i.addObserver(this));
        determineBestIterationTask = new FutureTask<>(new BestIterationComputation());
    }

    public void allIterationsRun() {
        System.out.println("All iteration run");
        if (bestIterationFoundBeforeAllEnd.get()) {
            System.out.println("Already resolving best iteration");
        } else {
            System.out.println("No best iteration found. Release the wait");
            allIterationsFinished.set(true);
            wait.set(false);
            synchronized (this) {
                notify();
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("An iteration found");
        bestIterationFoundBeforeAllEnd.set(true);
        wait.set(false);
        synchronized (this) {
            notify();
        }
    }

    public Iteration getBestIteration() {
        try {
            while (wait.get()) {
                //wait for all iterations to finish or wait for at least one fast finished
                synchronized (this) {
                    System.out.println("waiting");
                    wait();
                }
            }
            System.out.println("run future task");
            service.submit(determineBestIterationTask);
            Iteration o = determineBestIterationTask.get();
            return o;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            LOGGER.error("Error", e);
            throw new RuntimeException(e);
        }
    }


    class BestIterationComputation implements Callable<Iteration> {

        @Override
        public Iteration call() {
            System.out.println("Determining best iteration out of all iterations");
            ArrayList<Iteration> toSortIterations = new ArrayList<>(iterations);
            toSortIterations.sort(reverseOrder(Comparator.comparingDouble(Iteration::getPackedVolume)));
            Iteration iteration = toSortIterations.stream().findFirst().orElseThrow(() -> new IllegalStateException("No best Result"));
            System.out.println("Found best iteration");
            return iteration;
        }
    }
}
