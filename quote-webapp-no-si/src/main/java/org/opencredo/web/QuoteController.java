package org.opencredo.web;

import org.opencredo.provider.web.GetQuoteCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuoteController {



    @RequestMapping("/getquote")
    public String getQuote(@ModelAttribute GetQuoteCommand command){
        return "showquotes";
    }

    @RequestMapping("/showquoteform")
    public String showQuoteForm(Model model){
        model.addAttribute("getQuotesCommand", new GetQuoteCommand());
        return "quoteform";
    }
}
