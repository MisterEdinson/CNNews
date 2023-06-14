package com.example.cnnews.ui.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cnnews.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private val bundleArs: ArticleFragmentArgs by navArgs()
    private lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arg = bundleArs.article
        arg.let { news->
            binding.tvTitleNews.text = news.title.toString()
            binding.tvDate.text = news.publishedAt.toString()
            binding.tvDetailDescription.text = news.description.toString()
            Glide.with(this).load(news.urlToImage).into(binding.imageView)

            binding.btnClickWeb.setOnClickListener {
                try {
                    Intent()
                        .setAction(Intent.ACTION_VIEW)
                        .addCategory(Intent.CATEGORY_APP_BROWSER)
                        .setData(Uri.parse(takeIf { URLUtil.isValidUrl(news.url) }
                            ?.let {
                                news.url
                            } ?: "https://google.com"))
                        .let {
                            ContextCompat.startActivity(requireContext(), it, null)
                        }
                } catch (_: Exception) {

                }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                startActivity(browserIntent)
            }
        }
    }
}