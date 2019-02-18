package com.example.black.mysecrettodo.model;

    public class Todo {
        int id;
        String note;

        // constructors
        public Todo() {
        }

        public Todo(String note) {
            this.note = note;
        }

        public Todo(int id, String note) {
            this.id = id;
            this.note = note;
        }

        // setters
        public void setId(int id) {
            this.id = id;
        }

        public void setNote(String note) {
            this.note = note;
        }

        // getters
        public long getId() {
            return this.id;
        }

        public String getNote() {
            return this.note;
        }

    }