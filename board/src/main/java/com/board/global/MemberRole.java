package com.board.global;

    public enum MemberRole {
        ROLE_MEMBER("ROLE_MEMBER"),
        ROLE_ADMIN("ROLE_ADMIN");

        String role;

        MemberRole(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

