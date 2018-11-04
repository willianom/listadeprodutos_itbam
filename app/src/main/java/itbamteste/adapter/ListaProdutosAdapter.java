package itbamteste.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import itbamteste.bean.ProdutosBean;
import itbamteste.listadeprodutos.FragmentDetalheProduto;
import itbamteste.listadeprodutos.R;
import itbamteste.util.RoundedCornersTransformation;

public class ListaProdutosAdapter extends  RecyclerView.Adapter<ListaProdutosAdapter.SingleItemRowHolder>{

    private FragmentActivity  mContext;
    private List<ProdutosBean> itemsList;

    public static int sCorner = 15;
    public static int sMargin = 2;

    public ListaProdutosAdapter(FragmentActivity  mContext, List<ProdutosBean> itemsList) {

        this.mContext = mContext;
        this.itemsList = itemsList;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_lista_produto, parent, false);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                        ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);

        SingleItemRowHolder mh = new SingleItemRowHolder(mContext, v);

        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int position) {

        ProdutosBean singleItem = itemsList.get(position);

        double value = Double.parseDouble(singleItem.getPriceSuggested());

        String precoProduto = "R$" + String.format("%.2f", value );
        String nomeProduto  = singleItem.getName();
        String urlImage     = singleItem.getUrl();

        holder.txtNome.setText(nomeProduto);
        holder.txtPreco.setText(precoProduto);
        holder.beanProduto = itemsList.get(position);

        Glide.with (mContext)
                .load(urlImage)
                .bitmapTransform(new RoundedCornersTransformation( mContext,sCorner, sMargin))
                .thumbnail (0.5f)
                .centerCrop()
                .placeholder(R.drawable.ampulheta)
                .diskCacheStrategy (DiskCacheStrategy.ALL)
                .into(holder.imgProduto);
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ImageView imgProduto;
        protected TextView txtNome;
        protected TextView txtPreco;
        protected Button btnAddCarrinho;
        protected ProdutosBean beanProduto;
        protected FragmentActivity contextHolder;


        public SingleItemRowHolder(FragmentActivity context, View view) {
            super(view);

            this.imgProduto     = (ImageView) view.findViewById(R.id.imgview_produto);
            this.txtNome        = (TextView) view.findViewById(R.id.txt_nome_produto);
            this.txtPreco       = (TextView) view.findViewById(R.id.txt_preco_produto);
            this.btnAddCarrinho = (Button) view.findViewById((R.id.btn_add_carrinho));

            contextHolder = context;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle args = new Bundle ();

                    args.putSerializable ("bean_produto", beanProduto);

                    FragmentManager fm = contextHolder.getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    FragmentDetalheProduto viewRecipes = new FragmentDetalheProduto ();
                    viewRecipes.setArguments(args);
                    ft.replace(R.id.listproduct_fragment_container, viewRecipes);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });

            btnAddCarrinho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Adicionado ao carrinho.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
