package itbamteste.listadeprodutos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import itbamteste.adapter.ListaProdutosAdapter;
import itbamteste.bean.ListaProdutoBean;
import itbamteste.bean.ProdutosBean;
import itbamteste.bean.ProdutosParseJson;
import itbamteste.util.Converter;

public class FragmentListaProduto extends Fragment {

    private ListaProdutosAdapter listAdapter;
    private ProdutosParseJson    parserJsonProduto;
    private List<ProdutosBean>   listProduto;

    private RecyclerView mRecyclerView;

    private Gson gSon;
    private RequestQueue queue;
    private Toolbar toolbar;

    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu (true);

        gSon = new Gson ();
        queue = Volley.newRequestQueue (getActivity ());

        url = "http://ec2-54-171-222-219.eu-west-1.compute.amazonaws.com:8484/lista/";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_produto, container, false);

        mRecyclerView = (RecyclerView) view.findViewById (R.id.rcview_produtos);
        mRecyclerView.setHasFixedSize (true);

        LinearLayoutManager llm = new LinearLayoutManager (getActivity ());
        llm.setOrientation (LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager (llm);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Lista de Produtos");
        toolbar.setNavigationIcon(R.drawable.ic_menu_holo_light);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "Click sobre a navegação", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                parserJsonProduto = gSon.fromJson(response,ProdutosParseJson.class);
                listProduto = parserJsonProduto.getData().getProducts();

                listAdapter = new ListaProdutosAdapter (getActivity (), listProduto);
                mRecyclerView.setAdapter (listAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText (getActivity (), "Não foi possível carregar os dados.", Toast.LENGTH_SHORT).show ();

            }
        });

        queue.add (stringRequest);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.item_carrinho);
        menuItem.setIcon(Converter.convertLayoutToImage(getActivity(),2,R.drawable.mycart));
    }
}
