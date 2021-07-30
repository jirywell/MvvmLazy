package com.rui.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.rui.demo.BR;
import com.rui.demo.R;
import com.rui.demo.data.source.local.db.Person;
import com.rui.demo.databinding.TestFragmentRoomBinding;
import com.rui.demo.ui.viewmodel.RoomSampleViewModel;
import com.rui.mvvmlazy.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Create Date：2021/01/01
 * 实现Room数据的基本操作
 * zjr
 */

public class RoomSampleFragment extends BaseFragment<TestFragmentRoomBinding, RoomSampleViewModel> {

    List<Person> personList = new ArrayList<>();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.test_fragment_room;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.getAllWordsLive().observe(this, words -> {
            personList.clear();
            personList.addAll(words);
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < words.size(); i++) {
                Person person = words.get(i);
                text.append(person.getId()).append(":").append(person.getName()).append("  ").append(person.getAge()).append("\n");
            }
            binding.textView.setText(text.toString());
        });
        binding.buttonInsert.setOnClickListener(v -> {
            Person person = new Person("大壮", "11");
            viewModel.insertWords(person);
        });

        binding.buttonClear.setOnClickListener(v -> viewModel.deleteAllWords());

        binding.buttonUpdate.setOnClickListener(v -> {
            for (Person person : personList) {
                person.setName(person.getName() + "更新");
                person.setAge(String.valueOf(Integer.parseInt(person.getAge()) + 1));
                viewModel.updateWords(person);
            }
        });

        binding.buttonDelete.setOnClickListener(v -> {
            if (personList.size() > 0) {
                viewModel.deleteWords(personList.get(0));
            }
        });
    }
}
