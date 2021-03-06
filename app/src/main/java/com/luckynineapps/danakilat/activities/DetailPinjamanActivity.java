package com.luckynineapps.danakilat.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.luckynineapps.danakilat.R;
import com.luckynineapps.danakilat.models.pinjaman.Pinjaman;
import com.luckynineapps.danakilat.utils.CommonUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.luckynineapps.danakilat.data.Constant.WEB_URL_IMAGE_FINTECH;

public class DetailPinjamanActivity extends AppCompatActivity {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.txt_nama)
    TextView txtNama;
    @BindView(R.id.txt_bunga)
    TextView txtBunga;
    @BindView(R.id.txt_nominal)
    TextView txtNominal;
    @BindView(R.id.txt_deskripsi)
    TextView txtDeskripsi;
    @BindView(R.id.ad_bottom)
    AdView bottomAds;

    Pinjaman pinjaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pinjaman);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        pinjaman = (Pinjaman) getIntent().getSerializableExtra("pinjaman");

        initAd();
        initView();
    }

    private void initAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        bottomAds.loadAd(adRequest);
    }

    private void initView() {
        Picasso.get().load(WEB_URL_IMAGE_FINTECH + pinjaman.getImage()).into(image);
        txtTitle.setText(pinjaman.getNamaPinjaman());
        txtNama.setText(pinjaman.getNamaPinjaman());
        txtBunga.setText(pinjaman.getBunga());
        txtNominal.setText("Rp. " + CommonUtil.currencyFormat(Long.parseLong(pinjaman.getNominalPinjaman())));
        txtDeskripsi.setText(Html.fromHtml(pinjaman.getDeskripsiPanjang()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick({R.id.btn_back, R.id.btn_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_download:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pinjaman.getLinkAplikasi())));
                break;
        }
    }
}
