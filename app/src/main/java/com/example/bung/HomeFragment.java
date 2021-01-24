package com.example.bung;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.bung.R;

import com.example.bung.base.MethodFunction;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private DatabaseReference databaseReference;
    private TextView mSaldoUser;
    private TextView mPemasukanUser,mPengeluaranUser;
    private AnyChartView anyChartView;

    MethodFunction methodFunction = new MethodFunction();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view_profil = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        mPemasukanUser = view_profil.findViewById(R.id.tv_pemasukan_total);
        mPengeluaranUser = view_profil.findViewById(R.id.tv_pengeluaran_total);
        mSaldoUser = view_profil.findViewById(R.id.tv_profile_saldo);
        anyChartView = view_profil.findViewById(R.id.any_chart_view);

        setupPieChart();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int saldoPemasukan = Integer.parseInt(dataSnapshot.child("saldoPemasukan").getValue().toString());
                int saldoPengeluaran = Integer.parseInt(dataSnapshot.child("saldoPengeluaran").getValue().toString());
                int Total = saldoPemasukan - saldoPengeluaran;


                mSaldoUser.setText(methodFunction.currencyIdr(Total));
                mPemasukanUser.setText(methodFunction.currencyIdr(saldoPemasukan));
                mPengeluaranUser.setText(methodFunction.currencyIdr(saldoPengeluaran));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view_profil;
    }

    public void setupPieChart(){
        final Pie pie = AnyChart.pie();
        final List<DataEntry> data = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int saldoPemasukan = Integer.parseInt(dataSnapshot.child("saldoPemasukan").getValue().toString());
                int saldoPengeluaran = Integer.parseInt(dataSnapshot.child("saldoPengeluaran").getValue().toString());

                data.add(new ValueDataEntry("Pemasukkan", saldoPemasukan));
                data.add(new ValueDataEntry("Pengeluaran", saldoPengeluaran));
                pie.data(data);
                anyChartView.setChart(pie);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
