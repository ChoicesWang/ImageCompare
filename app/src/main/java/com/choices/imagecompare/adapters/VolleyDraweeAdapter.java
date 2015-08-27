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

import com.facebook.drawee.backends.volley.VolleyDraweeControllerBuilderSupplier;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.choices.imagecompare.Drawables;
import com.choices.imagecompare.configs.volley.SampleVolleyFactory;
import com.choices.imagecompare.holders.BaseViewHolder;
import com.choices.imagecompare.holders.VolleyDraweeHolder;
import com.choices.imagecompare.instrumentation.InstrumentedDraweeView;
import com.choices.imagecompare.instrumentation.PerfListener;

/**
 * RecyclerView Adapter for Volley using Drawee
 */
public class VolleyDraweeAdapter extends ImageListAdapter {

    public VolleyDraweeAdapter(
            Context context,
            PerfListener perfListener) {
        super(context, perfListener);
        final VolleyDraweeControllerBuilderSupplier supplier =
                new VolleyDraweeControllerBuilderSupplier(
                        context,
                        SampleVolleyFactory.getImageLoader(context));
        InstrumentedDraweeView.initialize(supplier);
    }

    @Override
    public VolleyDraweeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GenericDraweeHierarchy gdh = new GenericDraweeHierarchyBuilder(getContext().getResources())
                .setPlaceholderImage(Drawables.sPlaceholderDrawable)
                .setFailureImage(Drawables.sErrorDrawable)
                .build();
        InstrumentedDraweeView view = new InstrumentedDraweeView(getContext());
        view.setHierarchy(gdh);
        return new VolleyDraweeHolder(getContext(), parent, view, getPerfListener());
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<?> holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public void shutDown() {
        super.clear();
        InstrumentedDraweeView.shutDown();
        SampleVolleyFactory.getMemoryCache().clear();
    }
}
