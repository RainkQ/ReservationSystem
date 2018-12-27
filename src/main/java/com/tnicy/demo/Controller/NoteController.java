package com.tnicy.demo.Controller;

import com.tnicy.demo.Entity.Comment;
import com.tnicy.demo.Entity.User;
import com.tnicy.demo.Repository.NoteRepository;
import com.tnicy.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class NoteController {
@Autowired
    NoteRepository noteRepository;
@Autowired
    UserRepository userRepository;
    @GetMapping("/note")
    public String note(Model model,HttpSession session){
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        int uid=(int)session.getAttribute("uid");
        ArrayList<Comment> comments=(ArrayList<Comment>)noteRepository.findAll();
        model.addAttribute("comments",comments);
        model.addAttribute("uid",uid);
        model.addAttribute("user", session.getAttribute("user"));
        return "note";
    }

    @PostMapping("/writenote")
    public String writenote(Comment comment,HttpSession session){

        User user=(User) session.getAttribute("user");
        comment.setUsername(user.getUsername());
        comment.setUid(user.getUid());
        noteRepository.save(comment);
        return "redirect:/note";
    }

    @GetMapping("/userremovenote/{cid}")
    public String userremovenote(@PathVariable("cid") int cid,HttpSession session){
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        noteRepository.deleteById(cid);
        return "redirect:/note";
    }

    @GetMapping("noteControl")
    public String noteControl(Model model,HttpSession session){
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        ArrayList<Comment> comments=(ArrayList<Comment>)noteRepository.findAll();
        model.addAttribute("comments",comments);
        model.addAttribute("user", session.getAttribute("user"));
        return "noteControl";

    }

    @GetMapping("/removenote/{cid}")
    public String removenote(@PathVariable("cid") int cid,HttpSession session){
//        int uid=(int)session.getAttribute("uid");
//        User user=userRepository.findByUid(uid);

        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        noteRepository.deleteById(cid);
        return "redirect:/noteControl";
    }

}
