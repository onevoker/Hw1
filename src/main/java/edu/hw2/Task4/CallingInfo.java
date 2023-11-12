package edu.hw2.Task4;

public record CallingInfo(String className, String methodName) {
    public static CallingInfo callingInfo(Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();
        return new CallingInfo(className, methodName);
    }
}
