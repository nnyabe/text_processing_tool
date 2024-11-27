package com.text.text_processing_tool.service;

import com.text.text_processing_tool.models.RegexProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Provides methods for working with regular expressions, including matching,
 * counting, and replacing patterns in a given text.
 * Implements the {@link RegexProcessor} interface.
 */
public class RegexService implements RegexProcessor {

    @Override
    public boolean hasMatch(String text, String regex) throws PatternSyntaxException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }


    @Override
    public int countMatches(String text, String regex) {
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            count++;
        }

        return count;
    }


    @Override
    public String replaceText(String text, String regex, String replaceWord) {
        return text.replaceFirst(regex, replaceWord);
    }


    @Override
    public String replaceAllMatches(String text, String regex, String replaceWord) {
        return text.replaceAll(regex, replaceWord);
    }


    @Override
    public List<String> findRegexMatches(String text, String regex) {
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            matches.add(matcher.group());
        }

        return matches;
    }
}
