package com.carlosacademic;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.carlosacademic.model.CreateTodo;
import com.carlosacademic.proxy.TodoProxy;
import com.carlosacademic.service.TodoProcessor;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<CreateTodo, String> {

    private final TodoProxy proxy;
    private final TodoProcessor processor;

    public App() {
        this.proxy = new TodoProxy();
        this.processor = new TodoProcessor();
    }


    public String handleRequest(final CreateTodo input, final Context context) {
        String todo = proxy.getTodo(input.id(), context);
        return processor.processTodo(todo, context);
    }
}
