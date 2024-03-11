package com.br.app.core.utils;

import com.br.app.core.exeptions.RateLimitException;

import java.util.function.Supplier;

public class RetryUtil {

    public static void executeWithRetry(Runnable runnable, int maxRetries, long initialDelay) throws Exception {
        int attempt = 1;
        while (attempt <= maxRetries) {
            try {
                runnable.run();
                return; // Sucesso na primeira tentativa
            } catch (Exception e) {
                if (attempt == maxRetries) {
                    throw new RateLimitException("Excedido o limite de tentativas"); // Rethrow on last attempt
                }

                long delay = calculateBackoffDelay(attempt, initialDelay);
                Thread.sleep(delay);
                attempt++;
            }
        }
    }

    private static long calculateBackoffDelay(int attempt, long initialDelay) {
        // Implementar lógica de backoff (ex.: backoff exponencial)
        return initialDelay * (long) Math.pow(2, attempt - 1);
    }

}