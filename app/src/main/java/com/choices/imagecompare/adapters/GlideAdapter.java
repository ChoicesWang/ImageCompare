/*
 * This file provided by Facebook is for non-commercial testing and evaluation
 * purposes only.  Facebook reserves all rights not expressly granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * FACEBOOK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.choices.imagecompare.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.choices.imagecompare.holders.BaseViewHolder;
import com.choices.imagecompare.holders.GlideHolder;
import com.choices.imagecompare.instrumentation.InstrumentedImageView;
import com.choices.imagecompare.instrumentation.PerfListener;

/**
 * RecyclerView Adapter for Glide
 */
public class GlideAdapter extends ImageListAdapter {

    public GlideAdapter(
            Context context,
            PerfListener perfListener) {
        super(context, perfListener);
    }

    @Override
    public GlideHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final InstrumentedImageView instrumentedImageView = new InstrumentedImageView(getContext());
        return new GlideHolder(getContext(), parent, instrumentedImageView, getPerfListener());
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<?> holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public void shutDown() {
        Glide.get(getContext()).clearMemory();
    }
}
