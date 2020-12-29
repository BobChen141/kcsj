package bean;

import java.util.ArrayList;

public class Comments {
    private String username;
    private String comment;
    private String blogid;
    private String time;
    private String commentid;
    private int rate;
    private String superid;
    private ArrayList<Comments>commentlist;
    private String supername;

    public String getSupername() {
        return supername;
    }

    public void setSupername(String supername) {
        this.supername = supername;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public ArrayList<Comments> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(ArrayList<Comments> commentlist) {
        this.commentlist = commentlist;
    }


    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }



    public String getSuperid() {
        return superid;
    }

    public void setSuperid(String superid) {
        this.superid = superid;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBlogid() {
        return blogid;
    }

    public void setBlogid(String blogid) {
        this.blogid = blogid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
