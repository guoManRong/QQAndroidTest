package com.example.qq;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dell on 2019/1/2.
 */

public class ContextFragment extends Fragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.context_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.content_text);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String textValue = bundle.getString("textValue");
            textView.setText(textValue);
        }
        return view;
    }

}
