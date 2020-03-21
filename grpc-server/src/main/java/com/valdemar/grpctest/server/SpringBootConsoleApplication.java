package com.valdemar.grpctest.server;

import com.valdemar.grpctest.TravelDetails;
import com.valdemar.grpctest.TripDetailsRequest;
import com.valdemar.grpctest.TripDetailsResponse;
import com.valdemar.grpctest.TripDetailsServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class SpringBootConsoleApplication
        implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(SpringBootConsoleApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE SPRING BOOT APPLICATION - gRPC Server Application");
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        LOG.info(" SPRING BOOT APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8080)
                .addService(new TripDetailsServiceImpl()).build();

        System.out.println("Starting gRPC server...");
        server.start();
        System.out.println("gRPC Server started!");
        server.awaitTermination();
    }
}
