package com.fatmouf.fatmouf.Utilities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToSpannable {
    private final Context context;
    private final SpannableStringBuilder builder;
    private String text;
    private static final Pattern pattern = Pattern.compile("(.*?)(\\{.*?\\}).*", Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern drawablePattern = Pattern.compile("R.drawable.(.*)\\}", Pattern.MULTILINE | Pattern.DOTALL);
    private int textColor = Color.RED;
    private int textStyle = Typeface.NORMAL;

    public StringToSpannable setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public StringToSpannable setTextStyle(int textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public StringToSpannable(Context context, String text) {
        this.context = context;
        this.text = text;
        builder = new SpannableStringBuilder();
    }

    public Spannable toSpannable() {
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String firstGroup = matcher.group(1);
            String toSpanText = matcher.group(2);
            builder.append(firstGroup);
            if (toSpanText.contains("drawable")) {
                addImageSpan(toSpanText);
            } else {
                addTextSpan(toSpanText);
            }

            text = text.replaceFirst(firstGroup, "");
            toSpanText = toSpanText.replaceAll("\\{", "\\\\{");
            toSpanText = toSpanText.replaceAll("\\}", "\\\\}");
            text = text.replaceFirst(toSpanText, "");
            matcher = pattern.matcher(text);
        }
        builder.append(text);
        return builder;
    }

    private void addTextSpan(String text) {
        text = text.replaceAll("\\{", "");
        text = text.replaceAll("\\}", "");
        int imageStart = builder.length();
        builder.append(text);
        builder.setSpan(new ForegroundColorSpan(textColor), imageStart, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(textStyle), imageStart, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void addImageSpan(String text) {
        int imageStart = builder.length();
        builder.append(text);

        int drawableId = getDrawableId(text, context);
        ImageSpan image = new ImageSpan(context, drawableId, ImageSpan.ALIGN_BASELINE);
        builder.setSpan(image, imageStart, builder.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    private int getDrawableId(String secondGroup, Context context) {
        Matcher matcher = drawablePattern.matcher(secondGroup);
        if (matcher.find()) {
            String drawableName = matcher.group(1);
            return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
        } else {
            return 0;
        }
    }
}
