package com.example.android2homework1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2homework1.HomeAdapter;
import com.example.android2homework1.R;
import com.example.android2homework1.TaskModel;
import com.example.android2homework1.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    FragmentResultListener fragmentResultListener;
    HomeAdapter adapter;
    private ArrayList<TaskModel> list = new ArrayList<>();


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        getParentFragmentManager().setFragmentResultListener("form", getViewLifecycleOwner(), fragmentResultListener);
        getParentFragmentManager().setFragmentResultListener("formRed", getViewLifecycleOwner(), fragmentResultListener);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.action_nav_home_to_formfragment);
//                Navigation.findNavController()navController.navigate(R.id.action_nav_home_to_formfragment);
            }
        });
        initREcycle();
        return root;
    }

    private void initREcycle() {
        adapter = new HomeAdapter(list);
        binding.recycleView.setAdapter(adapter);
        fragmentResultListener = new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                Toast.makeText(requireContext(), "requestKey: " + requestKey, Toast.LENGTH_SHORT).show();
                switch (requestKey){
                    case "form":
                        TaskModel model = (TaskModel) result.getSerializable("task");
                        list.add(0,model);
                        Toast.makeText(requireContext(), model.getTitle().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case "formRed":
                        int position = result.getInt("position");
                        TaskModel model1 = (TaskModel) result.getSerializable("task");
                        list.remove(position);
                        list.add(position, model1);
                        break;
                }
            }
        };
        getChildFragmentManager().setFragmentResultListener("form", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                Toast.makeText(requireContext(), "requestKey: " + requestKey, Toast.LENGTH_SHORT).show();
                switch (requestKey){
                    case "form":
                        TaskModel model = (TaskModel) result.getSerializable("task");
                        list.add(0,model);
                        Toast.makeText(requireContext(), model.getTitle().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case "formRed":
                        int position = result.getInt("position");
                        TaskModel model1 = (TaskModel) result.getSerializable("task");
                        list.remove(position);
                        list.add(position, model1);
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}