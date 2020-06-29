package com.stu.thread.demo.message_queue;

import java.util.UUID;

public  class Message {
        private String id;
        private String value;

        Message(String value) {
            this.id = UUID.randomUUID().toString().replaceAll("-", "");
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "id='" + id + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }