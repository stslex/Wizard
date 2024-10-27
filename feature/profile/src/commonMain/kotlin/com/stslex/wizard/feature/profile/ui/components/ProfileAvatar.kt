package com.stslex.wizard.feature.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.stslex.core.ui.base.image.NetworkImage
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.profile.ui.model.ProfileAvatarModel

@Composable
fun ProfileAvatar(
    avatar: ProfileAvatarModel,
    modifier: Modifier = Modifier
) {
    val avatarModifier = modifier
        .size(AppDimension.Icon.large)
        .clip(RoundedCornerShape(AppDimension.Radius.medium))

    when (avatar) {
        is ProfileAvatarModel.Content -> {
            NetworkImage(
                url = avatar.url,
                modifier = avatarModifier
            )
        }

        is ProfileAvatarModel.Empty -> {
            Box(
                modifier = avatarModifier
                    .background(avatar.color),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = avatar.symbol,
                )
            }
        }
    }
}