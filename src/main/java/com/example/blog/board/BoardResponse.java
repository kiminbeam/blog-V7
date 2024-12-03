package com.example.blog.board;

import com.example.blog._core.util.MyDate;
import com.example.blog.reply.Reply;
import com.example.blog.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class UpdateFormDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        public UpdateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = MyDate.formatToStr(board.getCreatedAt());
        }
    }

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        private Integer userId;
        private String username;
        private boolean isOwner = false;

        private List<ReplyDTO> replies;

        @Data
        class ReplyDTO {
            private int id;
            private String comment;
            private int userId;
            private String username;

            public ReplyDTO(Reply reply) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
            }
        }

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = MyDate.formatToStr(board.getCreatedAt());

            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername(); // lazy loading
            if(sessionUser != null) {
                this.isOwner = sessionUser.getId() == board.getUser().getId();
            }
            this.replies = board.getReplies().stream().map(r -> new ReplyDTO(r)).toList();
        }
    }

    @Data
    public static class DTO {
        private int id;
        private String title;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
