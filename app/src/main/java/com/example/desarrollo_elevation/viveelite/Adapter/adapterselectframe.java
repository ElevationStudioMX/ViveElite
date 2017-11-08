package com.example.desarrollo_elevation.viveelite.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.desarrollo_elevation.viveelite.MainActivity_Editarphoto;
import com.example.desarrollo_elevation.viveelite.Model;
import com.example.desarrollo_elevation.viveelite.Modelselectframe;
import com.example.desarrollo_elevation.viveelite.MultiViewTypeAdapter;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.desarrollo_elevation.viveelite.spotify.serch_playlist;
import com.example.photogesturelibrary.PhotoView;
import com.example.photogesturelibrary.PhotoViewAttacher;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;

import static android.R.attr.canPerformGestures;
import static android.R.attr.factor;
import static android.R.attr.inflatedId;
import static android.R.attr.y;
import static com.example.desarrollo_elevation.viveelite.R.id.image;
import static com.example.desarrollo_elevation.viveelite.R.id.imageView;

/**
 * Created by Desarrollo_Elevation on 30/01/17.
 */

public class adapterselectframe extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Modelselectframe> dataSet;
    Context mContext;
    int total_types;
    //MediaPlayer mPlayer;

    private static float densityDpi;
    private boolean fabStateVolume = false;
   // ImageView marco;
int pose =-1;
    private ImageView mar;



    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        //TextView txtType;
        ImageView image;
        ImageView marco;
       // TextView detalleimg;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            //this.txtType = (TextView) itemView.findViewById(R.id.txttextoimg);
            this.image = (ImageView) itemView.findViewById(R.id.imagemarcosparaseleccionar);
            this.marco = (ImageView)itemView.findViewById(R.id.imagenviewmarco);


         /*   this.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    marco.setImageResource(R.drawable.marco2);
                }
            });*/

            //this.detalleimg=(TextView) itemView.findViewById(R.id.btndetalleimg);

        }


    }






    public adapterselectframe(ArrayList<Modelselectframe> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
       // View view1;
        switch (viewType) {

            case Modelselectframe.IMAGE_TYPE:
              view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marcos, parent, false);
            return new adapterselectframe.ImageTypeViewHolder(view);

            /*view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main__editarphoto, parent, false);
            return new adapterselectframe.ImageTypeViewHolder(view1);*/

              }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:

                return Modelselectframe.IMAGE_TYPE;

            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final Modelselectframe object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {

                case Modelselectframe.IMAGE_TYPE:


                   // ((ImageTypeViewHolder) holder).image.setImageResource(object.img);

                    Picasso.with(mContext).load(object.img).resize(250,0).into(((ImageTypeViewHolder) holder).image);


            //  final int dato = object.img;


                //    final int imagen= object.img;
                //    int imagen = ((adapterselectframe.ImageTypeViewHolder) holder).image
                  //  int imagen =  ((adapterselectframe.ImageTypeViewHolder) holder).image.setImageResource(object.img);
                    ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View view) {

                          //  LayoutInflater inflater = getLayoutInflater();
                           // View myView = inflate
                            // r.inflate(R.layout.main, null);


                            final MainActivity_Editarphoto editarphoto = new MainActivity_Editarphoto();

                           final ImageView imgenes= editarphoto.retornartext();


                             try {


                             URL newurl = new URL(object.img);
                             Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                                 imgenes.setImageBitmap(mIcon_val);
                             }

                             catch (IOException e)
                                {
                                    Log.v("psao por aqui ","Liena 177");
}



                          // imgenes.setImageResource(object.img);



                            //Picasso.with(mContext).load(object.img).resize(250,0).into(imgenes);


                            editarphoto.retornoposicionmarco(listPosition);

                            final PhotoView photoView = editarphoto.getImageView();
                            final RelativeLayout vista = editarphoto.getCamView();

                            final PhotoViewAttacher attacher =  editarphoto.getA();

                            ViewFlipper viewFlipper = editarphoto.getViewFlipper();





                            vista.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                            //int datosrelativos = Relativoinferior.getMeasuredHeight();

                            int vh = vista.getMeasuredHeight();
                            int vw = vista.getMeasuredWidth();

                          //  Log.v("tamaño", "hieght "+vh+"  width "+vw);


                            Log.v("vista", ""+vh+" "+vw);

                            int h =imgenes.getDrawable().getIntrinsicHeight();
                            int w = imgenes.getDrawable().getIntrinsicWidth();
                            Activity activity = new Activity();
                            DisplayMetrics displayMetrics = new DisplayMetrics();
                            WindowManager windowManager = (WindowManager) mContext
                                    .getSystemService(Context.WINDOW_SERVICE);
                            /*getWindowManager()*/ windowManager.getDefaultDisplay().getMetrics(displayMetrics);

                            int height = displayMetrics.heightPixels;
                            final int width = displayMetrics.widthPixels;

                            DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
                            densityDpi = metrics.density;


                            Log.v("psao por aqui ","Liena 218");

//                            photoView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);


                            final int alturaimagen = photoView.getDrawable().getIntrinsicHeight();
                            final int anchuraimagen =photoView.getDrawable().getIntrinsicWidth();


                            final int heigthview = editarphoto.getHeigthview();

                           // Log.v("altura view", ""+heigthview);
                          //  Log.v("anchura imagen", ""+anchuraimagen);

                            //Picasso.with(mContext).load(object.img).resize(500,0).into(imgenes);

                            Log.v("psao por aqui ","Liena 234");
                    //        Log.v("Condiciones victorianos", "view  la altura "+vista.getLayoutParams().height+" ancho"+vista.getLayoutParams().width+" imagen height "+alturaimagen+" width "+anchuraimagen+" marco heigt "+h+ "width "+w+ " ");

                            if(object.img.equals("http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers/no-marco.png")){
                                Log.v("psao por aqui ","Liena 238");

                             /*  new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {


                                                Log.i("tag", "This'll run 300 milliseconds later");
                                attacher.setScale(1.0f);

*/

                               /* new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {


                                                Log.i("tag", "This'll run 300 milliseconds later");

*/



                                int posion = listPosition;

                             //   Log.v("posision entrada", ""+posion);
                             //   Log.v("altura entrada", ""+heigthview +" "+vista.getLayoutParams().height);

                                imgenes.setVisibility(View.INVISIBLE);
                                viewFlipper.setVisibility(View.INVISIBLE);
                                float proporcionimage = (float) alturaimagen/anchuraimagen;

                                Log.v("psao por aqui ","Liena 270");
                                float proporcionview = (float) heigthview/width;

                                float anchoimag = (float) heigthview / proporcionimage;

                                final float alturaimagenes = (float) proporcionimage * width;


                                Log.v("psao por aqui ","Liena 278");
                                //final int alturadispositovoimagen =(int)(proporcionimage*width);


                              //  Log.v("conciciones victoriana2", heigthview + "/ "+width+" = "+ proporcionview + " < "+proporcionimage+" = " +alturaimagen+" / "+anchuraimagen);

                                if(proporcionview <proporcionimage )
                                {Log.v("psao por aqui ","Liena 285");

                                    Log.v("vista1", ""+vista.getLayoutParams().height);
                                    vista.getLayoutParams().height = heigthview;
                                    vista.getLayoutParams().width = (int)anchoimag;
                                    Log.v("psao por aqui ","Liena 290");
                                    photoView.getLayoutParams().height = heigthview;
                                    Log.v("psao por aqui ","Liena 292");
                                   // vista.setGravity(RelativeLayout.CENTER_HORIZONTAL);
                                    int posionsal = listPosition;


//                                    Log.v("Condicionesvictorianav1", "view altura "+vista.getLayoutParams().height+ " anchura "+vista.getLayoutParams().width+" imagen" +photoView.getLayoutParams().height);
                               //     Log.v("posision salida", ""+posionsal);
                                  //Log.v("altura salida1", ""+(int)heigthview);

                                }

                                else{

                                    Log.v("psao por aqui ","Liena 305");


                                                    Log.v("altura de iamgen", " "+alturaimagenes);


                                                    photoView.getLayoutParams().height = (int)alturaimagenes;
                                                    vista.getLayoutParams().height = (int)alturaimagenes;
                                                    Log.i("tag", "This'll run 300 milliseconds later");
                                                  //  attacher.setScale(1.0f);
                                                   // Log.v("vista2", ""+vista.getLayoutParams().height);

                                                    Log.v("Condicionesvictorianav2", "view altura " +vista.getLayoutParams().height +" imagen altura"+photoView.getLayoutParams().height);
                                                }



                                  // vista.getLayoutParams().width = 540;
                                    Log.v("psao por aqui ","Liena 310");
                                    Log.v("vista2", ""+vista.getLayoutParams().height);
                                    Log.v ("heigt ",""+ vista.getHeight());

                                                                  //  Log.v("tiempo de view", "view.....");






                                    // Log.v("fue ha esta conid", "esta pendiente");

                                  //  Log.v("Condicionesvictorianav2", "view altura " +vista.getLayoutParams().height +" imagen altura"+photoView.getLayoutParams().height);


                                    int posionsal = listPosition;

                                //    Log.v("posision salida", ""+posionsal);
                               //     Log.v("altura salida2", ""+vista.getLayoutParams().height+" "+heigthview);

                                }

                                     //       }
                                      //  },
                                       // 10000);



                                   // vista.getLayoutParams().height = alturadispositovoimagen;









else {
                                Log.v("psao por aqui ","Liena 352");
                                int posion = listPosition;

                                viewFlipper.setVisibility(View.VISIBLE);
                               // Log.v("posision entrada", ""+posion);
                              //  Log.v("altura entrada", ""+vista.getLayoutParams().height+" "+heigthview);

                         imgenes.setVisibility(View.VISIBLE);
                                Log.v("psao por aqui ","Liena 359");

                            final float proporcion =(float)h/w;
                                Log.v("psao por aqui ","Liena 362");


                            final int alturadispositovo = (int) (proporcion * width);

                              //  Log.v("ultima formula", ""+alturadispositovo);




                            //final AbsoluteLayout vista = editarphoto.getCamView();








                            final float proporcion2 = (float)w/h;

                            final float proporcionimagen = (float) anchuraimagen/alturaimagen;
                                Log.v("psao por aqui ","Liena 396");

                                vista.getLayoutParams().height = alturadispositovo;
                                Log.v("psao por aqui ","Liena 387");
                    //            Log.v("condiciones victoriana3", +h+ " / " +w+" = "+proporcion+ " *" + width+ " = " +alturadispositovo+ "   "+anchuraimagen+ " / " +alturaimagen+ " = " +proporcionimagen+" <  " +w+ "/ "+h+ " = "+proporcion2);






                                Log.v("psao por aqui ","Liena 395");

                            ViewTreeObserver b = photoView.getViewTreeObserver();
                            b.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                @Override
                                public void onGlobalLayout() {
                                    Log.v("psao por aqui ","Liena 400");
                                   photoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                    Log.v("psao por aqui ","Liena 403");




                                  //  Log.v("condicion", "imagen "+proporcionimagen+" marco"+proporcion2);

                                    if (proporcionimagen < proporcion2)
                                    {
                                        Log.v("psao por aqui ","Liena 411");

                                       // Log.v("caso 1", "caso 1");
                                        float segundaanchira= proporcion2 * alturaimagen;

                                        final float scalahorizontal= segundaanchira / anchuraimagen;

                                      //  Log.v("imagen", "heigt "+alturaimagen+" width "+anchuraimagen);
                                       // Log.v("datos altura", ""+segundaanchira);
                                      //  Log.v("datos altura", ""+scalahorizontal);
                                                                                       //vista.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                                        Log.v("psao por aqui ","Liena 423");

                                               PhotoViewAttacher attacher = editarphoto.getA();//new PhotoViewAttacher(photoView);
                                               attacher.setScale(scalahorizontal /*true*/);
                                        Log.v("psao por aqui ","Liena 427");
                                        /*photoView.post(new Runnable() {
                                            @Override
                                            public void run() {


                                            }
                                        });*/



                                  ///      Log.v("Condicionesvictorianav3", proporcion2 +" * "+ alturaimagen +" = "+segundaanchira+ " / "+anchuraimagen +" = "+scalahorizontal);


                                           //     Log.v("scala attacher", ""+attacher.getScale());

                                                                             }


                                    else{
                                        Log.v("psao por aqui ","Liena 447");
                                       // Log.v("caso 2", "caso 2");

                                        float alturareal= proporcion* anchuraimagen;

                                        final float scalvertical = alturareal/alturaimagen;
                                        Log.v("psao por aqui ","Liena 453");
                                    //    Log.v("altura", ""+alturareal);
                                    //    Log.v("vertical", ""+scalvertical);



                                                final PhotoViewAttacher attacher =  editarphoto.getA();

                                        attacher.setScale(scalvertical /*true  */           );

                                        Log.v("psao por aqui ","Liena 463");
                                             //   Log.v("scala attacher", ""+attacher.getScale());

                           //             Log.v("Condicionesvictorianav4", proporcion +" * "+ anchuraimagen +" = "+alturareal+ " / "+alturaimagen +" = "+scalvertical);

                                                                           }






                                }
                                                        });

                               // int posionsal = listPosition;

                               // Log.v("posision salida", ""+posionsal);
                               // Log.v("altura salida3", ""+vista.getLayoutParams().height+" "+heigthview);
                                Log.v("psao por aqui ","Liena 482");
}






                          /*  Log.v("imagen", "heigt "+alturaimagen+" width "+anchuraimagen);
                            Log.v("datos altura", ""+segundaanchira);
                            Log.v("datos altura", ""+scalahorizontal);*/


                          //  OnLayoutChangeListener();
                           // vista.OnLayoutChangeListener;



                            /*vista.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                                @Override
                                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {

                                    Log.v("impri", ""+view.getLayoutParams().height+" "+vista.getLayoutParams().height+" "+i1+" "+i2+" "+i3+" "+i4+" "+i5+" "+i6+" "+i7);

                                 if(vista.getLayoutParams().height == view.getLayoutParams().height)
                                 {
                                    PhotoView photoView = editarphoto.getImageView();

                                    PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);




                                    attacher.setScale(1.85f);

                                    Log.v("scala attacher", ""+attacher.getScale());
                                }
                                }
                            });*/

                           /* new View().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                                @Override
                                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft,
                                                           int oldTop, int oldRight, int oldBottom) {
                                    // TODO Auto-generated method stub
                                }
                            });*/


                         /* new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            Log.i("tag", "This'll run 300 milliseconds later");

                                            PhotoView photoView = editarphoto.getImageView();

                                            PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);


                                            attacher.setScale(1.85f);

                                            Log.v("scala attacher", ""+attacher.getScale());
                                        }
                                    },
                                    1);*/


                          /*  Log.v("Datos de la h y w", "Height "+h+" Whidth"+w);
                            Log.v("Datos del dispositvo", "Height "+height+" Whidth"+width);
                            Log.v("proporcion; ",""+proporcion);
                            Log.v("altura dsp;", ""+alturadispositovo);*/









                            //((ImageTypeViewHolder)holder).image.setBackgroundColor(Color.parseColor("#d5bcf6"));

                         ///   int pos = editarphoto.getposition(listPosition);

                            //int pos = editarphoto.retornoposicionmarco();


                            //imgenes.setImageResource(object.img);

                          /*  Intent intent = new Intent(mContext, MainActivity_Editarphoto.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("dato", object.img);
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);*/
                           // mContext..startService(intent);


//                            MainActivity_Editarphoto a = new MainActivity_Editarphoto();


  //                          a.cambio_imagen(object.img);

  //                          Toast.makeText(mContext, "dato= "+dato, Toast.LENGTH_LONG).show();
                //   mar =(ImageView)a.findViewById(R.id.imagenviewmarco);

//                          mar.setImageResource(dato);
//                            Picasso.with(mContext).load(object.img).resize(500, 0).into(((adapterselectframe.ImageTypeViewHolder) holder).marco);
                            //((adapterselectframe.ImageTypeViewHolder) holder).marco.setImageResource(R.drawable.marco2);

                                 //   Toast.makeText(mContext, "pulsastes este mono"+ listPosition, Toast.LENGTH_LONG).show();




                        }
                    });
                    break;



            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    private class InsideWebViewClient extends WebViewClient {
        @Override
        // Force links to be opened inside WebView and not in Default Browser
        // Thanks http://stackoverflow.com/a/33681975/1815624
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}

