package br.com.williamfranco.chuckerktor.src.features.posts.repositories

import br.com.williamfranco.chuckerktor.src.common.constants.ApiConstant
import br.com.williamfranco.chuckerktor.src.common.patterns.ResultPattern
import br.com.williamfranco.chuckerktor.src.common.services.HttpService
import br.com.williamfranco.chuckerktor.src.features.posts.exceptions.PostException
import br.com.williamfranco.chuckerktor.src.features.posts.models.PostModel

interface PostRepository {
    suspend fun getPosts(): ResultPattern<List<PostModel>, PostException>
}

class PostRepositoryImpl(
    private val httpService: HttpService,
) : PostRepository {

    override suspend fun getPosts(): ResultPattern<List<PostModel>, PostException> {
        return try {
            val posts: List<PostModel> = httpService.getData(ApiConstant.POSTS)
            ResultPattern.Success(posts)
        } catch (error: Exception) {
            ResultPattern.Error(PostException("Failed to load posts: $error"))
        }
    }
}
