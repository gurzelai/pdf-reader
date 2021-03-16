package com.example.pdfreader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import org.w3c.dom.Text;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public class MostrarPDF extends Activity implements OnPageChangeListener, OnLoadCompleteListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    PDFView pdfView;
    TextView tv_cabecera;
    Integer pageNumber = 0;
    String pdfFileName;
    String nombre;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        pdfView= (PDFView)findViewById(R.id.pdfView);
        tv_cabecera = (TextView) findViewById(R.id.tv_cabecera);
        tv_cabecera.setText(getIntent().getExtras().getString("nombre"));
        int pos = (int) getIntent().getExtras().get(MainActivity.EXTRA_PATH);
        PDF pdf = MainActivity.getPdf(pos);
        try {
            path = UriFunciones.getPath(getApplicationContext(), pdf.getUri());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        displayFromAsset("gorka");
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;
        pdfView.fromAsset(path)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }
    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
}
