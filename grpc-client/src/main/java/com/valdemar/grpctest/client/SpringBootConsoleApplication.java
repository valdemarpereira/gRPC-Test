package com.valdemar.grpctest.client;

import com.valdemar.grpctest.TripDetailsRequest;
import com.valdemar.grpctest.TripDetailsResponse;
import com.valdemar.grpctest.TripDetailsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootConsoleApplication
        implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(SpringBootConsoleApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE SPRING BOOT APPLICATION");
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        LOG.info(" SPRING BOOT APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();


        TripDetailsServiceGrpc.TripDetailsServiceBlockingStub stub = TripDetailsServiceGrpc.newBlockingStub(channel);


        TripDetailsResponse tripDetailsResponse = stub.tripDetails(TripDetailsRequest.newBuilder()
                .build());

        System.out.println("Response received from server:\n" + tripDetailsResponse);

        channel.shutdown();
    }
}
