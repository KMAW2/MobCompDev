package ru.mirea.ivanovea.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textview.MaterialTextView;

public class DataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        SpannableString spannableString = new SpannableString("Zware - это крепкий табак состоящий из Кентукки, который проходит специальную огневую сушку и поэтому ему свойственный своеобразный копчёный аромат." +
                                                            " Отдельный интерес представляет сам процесс копчения табака. Связанные пучки табачных листьев подвешиваются и на протяжении нескольких дней сушат естественным образом "+
                                                            "до того момента пока листья не приобретут желтоватый оттенок. Только после того, как лист начнет желтеть, под ним разводят огонь. Табак держат над огнём при высокой, "+
                                                            "практически стопроцентной влажности, доводя температуру в закрытом помещении до 40 (а в некоторых случаях до 60–70) градусов, на протяжении нескольких дней, в течение" +
                                                            " которых листья меняют свой цвет и становятся тёмно-коричневыми. После этого листьям дают немного «проветриться», и в заключение проводят «финишное» копчение в дыму " +
                                                            "уже без открытого огня и при более низких температурах. То есть листья достигают состояния готовности над жаром углей, дым которых привносит характерную, узнаваемую «вкусную» ноту.");
        if (spannableString.length() > 0) {
            spannableString.setSpan(new LeadingMarginSpan.Standard(120, 0), 0, 1, 0);
        }
        MaterialTextView textView = view.findViewById(R.id.textView);
        textView.setText(spannableString);

        return view;
    }
}