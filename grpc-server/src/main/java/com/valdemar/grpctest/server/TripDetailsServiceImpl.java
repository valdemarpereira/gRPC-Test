package com.valdemar.grpctest.server;

import com.valdemar.grpctest.TripDetailsRequest;
import com.valdemar.grpctest.TripDetailsResponse;
import com.valdemar.grpctest.TripDetailsServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;

public class TripDetailsServiceImpl extends TripDetailsServiceGrpc.TripDetailsServiceImplBase {

    @Override
    public void tripDetails(
            TripDetailsRequest request, StreamObserver<TripDetailsResponse> responseObserver) {

        System.out.println("Request received from client:\n" + request);

        TripDetailsResponse response = TripDetailsResponse.newBuilder()
                .setTripId(request.getTripId())
                .setDate(LocalDateTime.now().toString())
                .setDays(5)
                .setFrom("London, UK")
                .setTo("Porto, PT")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
