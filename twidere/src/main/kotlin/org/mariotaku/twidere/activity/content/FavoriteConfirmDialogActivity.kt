/*
 *             Twidere - Twitter client for Android
 *
 *  Copyright (C) 2012-2017 Mariotaku Lee <mariotaku.lee@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.activity.content

import org.mariotaku.twidere.fragment.status.FavoriteConfirmDialogFragment
import org.mariotaku.twidere.model.ParcelableStatus
import org.mariotaku.twidere.model.UserKey

/**
 * Opens [FavoriteConfirmDialogFragment] to favorite a status
 *
 * Created by mariotaku on 2017/4/12.
 */
class FavoriteConfirmDialogActivity : AbsStatusDialogActivity() {

    override fun showDialogFragment(accountKey: UserKey, statusId: String, status: ParcelableStatus?) {
        executeAfterFragmentResumed {
            FavoriteConfirmDialogFragment.show(it.supportFragmentManager, accountKey, statusId, status)
        }
    }

}
