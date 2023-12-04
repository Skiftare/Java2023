package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaConversionException;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    //options to run
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(1))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(1))
            .threads(12)
            .build();

        new Runner(options).run();
    }

    record Student(String name, String surname) {
    }

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Supplier<String> supplier;

    //given: student & some ways to reflect
    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");
        method = Student.class.getMethod("name");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class));

        MethodType methodType = MethodType.methodType(String.class);
        MethodHandle nameMethod = lookup.findVirtual(Student.class, "name", methodType);

        CallSite site = LambdaMetafactory.metafactory(
            lookup,
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            nameMethod,
            methodType
        );

        MethodHandle methodHandle = site.getTarget();
        supplier = (Supplier<String>) methodHandle.invokeExact(student);
    }

    //when: make factory
    @Benchmark
    public void metaFactory(Blackhole bh) {
        String name = supplier.get();
        //then: expect that nothing will crash
        bh.consume(name);
    }

    //when: create directAccess
    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        //then: expect that it will be the fastest
        bh.consume(name);
    }


    //when: make reflection
    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        //then: it will slow.
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    //when: we make methodHandle
    @Benchmark
    public void methodHandle(Blackhole bh) throws Throwable {
        //then: nothing will crash & will work quite as Factory
        String name = (String) methodHandle.invokeExact(student);
        bh.consume(name);
    }

}
