package com.javarush.task.task25.task2506;

/**
 * Created by Борозденец on 10.07.2017.
 */
public class LoggingStateThread extends Thread {
    private Thread logThread;
    private State prevState;

    public LoggingStateThread(Thread thread) {
        logThread = thread;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            State state = logThread.getState();
            if (prevState == null) {
                prevState = state;
                System.out.println(state.toString());
            } else if (!state.equals(prevState)) {
                System.out.println(state.toString());
                prevState = state;
            }
            if (state.equals(State.TERMINATED)) {
                break;
            }
        }
    }
}
