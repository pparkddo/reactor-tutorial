package com.pparkddo.reactortutorial;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Main {

    public static void main(String[] args) throws Exception {
        execOnAnotherThread();
    }

    // 모든 작업을 메인쓰레드에서 실행
    // [실행결과]
    // start
    // This is just mono
    // end
    private static void execOnMainThread() {
        System.out.println("start");
        Mono.just("This is just mono").subscribe(System.out::println);
        System.out.println("end");
    }

    // start, end print 는 메인쓰레드
    // Mono 작업을 parallel 쓰레드에서 실행
    // [실행결과]
    // start
    // end
    // This is just mono
    private static void execOnAnotherThread() throws InterruptedException {
        Scheduler scheduler = Schedulers.parallel();
        System.out.println("start");
        Mono.just("This is just mono").subscribeOn(scheduler).subscribe(System.out::println);
        System.out.println("end");
        Thread.sleep(1000);  // Mono subscribe 가 끝나기전에 종료하는 것을 방지
    }
}
