package com.example.patternbuilderfactory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.patternbuilderfactory.AbstractCommons.AntivirusSoftware;
import com.example.patternbuilderfactory.AbstractCommons.Factory;
import com.example.patternbuilderfactory.DetectorAntivirusSoftwareCommons.DetectorAsFactory;
import com.example.patternbuilderfactory.DoctorAntivirusSoftwareCommons.DoctorAsFactory;
import com.example.patternbuilderfactory.RecyclerViewAdapter.DividerItemDecoration;
import com.example.patternbuilderfactory.RecyclerViewAdapter.MyRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AntivirusSoftware detectorAntivirusSoftware;
    private AntivirusSoftware doctorAntivirusSoftware;

    private Factory detectorAsFactory;
    private Factory doctorAsFactory;


    private List<AntivirusSoftware> antivirusSoftwares;

    private Button detector;
    private Button doctor;

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        detector = findViewById(R.id.detector);
        doctor = findViewById(R.id.doctor);

        antivirusSoftwares = new ArrayList<>();
        detectorAsFactory = new DetectorAsFactory();
        doctorAsFactory = new DoctorAsFactory();

        detectorAntivirusSoftware = detectorAsFactory.createAntivirusSoftware();
        doctorAntivirusSoftware = doctorAsFactory.createAntivirusSoftware();

        adapter = new MyRecyclerViewAdapter(antivirusSoftwares);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(10);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(itemAnimator);


        detector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                antivirusSoftwares.add(detectorAntivirusSoftware);
                runLayoutAnimation(recyclerView);
                randomProblem();
            }

        });

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                antivirusSoftwares.add(doctorAntivirusSoftware);
                runLayoutAnimation(recyclerView);
                randomProblem();

            }
        });
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void showToastSebec(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Важное сообщение!")
                .setMessage("Клиенту не понравился Антивирус, он не будет платить((((")
                .setIcon(R.mipmap.sebec_launcher_foreground)
                .setCancelable(false)
                .setNegativeButton("Оставить аванс себе",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void showToastDestroy(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Важное сообщение!")
                .setMessage("Вас уволили")
                .setIcon(R.mipmap.sebec_launcher)
                .setCancelable(false)
                .setNegativeButton("Начать заново",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void showToastCat(View view) {
        Toast toastCat = Toast.makeText(getApplicationContext(),
                "Вы отлино справляетесь с заказами, прирожденный менеджер",
                Toast.LENGTH_LONG);
        toastCat.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastContainer = (LinearLayout) toastCat.getView();
        ImageView catImageView = new ImageView(getApplicationContext());
        catImageView.setImageResource(R.mipmap.cat_launcher_foreground);
        toastContainer.addView(catImageView, 0);
        toastCat.show();
    }

    public void destroyAll() {
        adapter.clearAll();
    }

    public void randomProblem() {
        int listSize = adapter.getItemCount();
        if (listSize % 3 == 0) {
            showToastSebec(recyclerView);
        } else if (listSize % 2 == 0) {
            showToastCat(recyclerView);
        }
        if (listSize >= 7) {
            destroyAll();
            showToastDestroy(recyclerView);
        }
    }
}
