package com.bee.master.common.utils;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

public class SnowFlake {

    private static final int TOTAL_BITS = 64;
    private static final int EPOCH_BITS = 42;
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;
    private static final int maxNodeId = (int) (Math.pow(2, NODE_ID_BITS) - 1);
    private static final int maxSequence = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    private static final long CUSTOM_EPOCH = 1557131988965L;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    private static SnowFlake snowFlake;

    static {
        snowFlake = new SnowFlake();
    }

    public static synchronized long nextIdentity() {
        return snowFlake.generate();
    }

    private long generate() {
        int nodeId = createNodeId();
        return createNextId(nodeId);
    }

    private synchronized long createNextId(int nodeId) {
        long currentTimestamp = timestamp();
        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = currentTimestamp;
        long id = currentTimestamp << (TOTAL_BITS - EPOCH_BITS);
        id |= (nodeId << (TOTAL_BITS - EPOCH_BITS - NODE_ID_BITS));
        id |= sequence;
        return id;
    }

    private int createNodeId() {
        int nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (byte macByte : mac) {
                        sb.append(String.format("%02X", macByte));
                    }
                }
            }
            nodeId = sb.toString().hashCode();
        } catch (Exception exception) {
            nodeId = (new SecureRandom().nextInt());
        }
        nodeId = nodeId & maxNodeId;
        return nodeId;
    }

    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }

}
