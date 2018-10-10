package com.guanhang.nettyguide.codec.marshalling;


import org.jboss.marshalling.ExceptionListener;

public class MarshallingException implements ExceptionListener {
    @Override
    public void handleMarshallingException(Throwable problem, Object subject) {
        System.out.println(subject);
        problem.printStackTrace();
        System.out.println("marsh failed");
    }

    @Override
    public void handleUnmarshallingException(Throwable problem, Class<?> subjectClass) {
        problem.printStackTrace();
        System.out.println("unmarsh failed");
    }

    @Override
    public void handleUnmarshallingException(Throwable problem) {
        problem.printStackTrace();
        System.out.println("unmarsh failed");
    }
}
