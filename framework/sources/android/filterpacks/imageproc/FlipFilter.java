package android.filterpacks.imageproc;

import android.app.slice.Slice;
import android.app.slice.SliceItem;
import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
/* loaded from: classes.dex */
public class FlipFilter extends Filter {
    private Program mProgram;
    @GenerateFieldPort(hasDefault = true, name = "vertical")
    private boolean mVertical = false;
    @GenerateFieldPort(hasDefault = true, name = Slice.HINT_HORIZONTAL)
    private boolean mHorizontal = false;
    @GenerateFieldPort(hasDefault = true, name = "tile_size")
    private int mTileSize = 640;
    private int mTarget = 0;

    public FlipFilter(String name) {
        super(name);
    }

    @Override // android.filterfw.core.Filter
    public void setupPorts() {
        addMaskedInputPort(SliceItem.FORMAT_IMAGE, ImageFormat.create(3));
        addOutputBasedOnInput(SliceItem.FORMAT_IMAGE, SliceItem.FORMAT_IMAGE);
    }

    @Override // android.filterfw.core.Filter
    public FrameFormat getOutputFormat(String portName, FrameFormat inputFormat) {
        return inputFormat;
    }

    public void initProgram(FilterContext context, int target) {
        switch (target) {
            case 3:
                ShaderProgram shaderProgram = ShaderProgram.createIdentity(context);
                shaderProgram.setMaximumTileSize(this.mTileSize);
                this.mProgram = shaderProgram;
                this.mTarget = target;
                updateParameters();
                return;
            default:
                throw new RuntimeException("Filter Sharpen does not support frames of target " + target + "!");
        }
    }

    @Override // android.filterfw.core.Filter
    public void fieldPortValueUpdated(String name, FilterContext context) {
        if (this.mProgram != null) {
            updateParameters();
        }
    }

    @Override // android.filterfw.core.Filter
    public void process(FilterContext context) {
        Frame input = pullInput(SliceItem.FORMAT_IMAGE);
        FrameFormat inputFormat = input.getFormat();
        if (this.mProgram == null || inputFormat.getTarget() != this.mTarget) {
            initProgram(context, inputFormat.getTarget());
        }
        Frame output = context.getFrameManager().newFrame(inputFormat);
        this.mProgram.process(input, output);
        pushOutput(SliceItem.FORMAT_IMAGE, output);
        output.release();
    }

    private void updateParameters() {
        boolean z = this.mHorizontal;
        float y_origin = 0.0f;
        float height = 1.0f;
        float x_origin = z ? 1.0f : 0.0f;
        boolean z2 = this.mVertical;
        if (z2) {
            y_origin = 1.0f;
        }
        float width = z ? -1.0f : 1.0f;
        if (z2) {
            height = -1.0f;
        }
        ((ShaderProgram) this.mProgram).setSourceRect(x_origin, y_origin, width, height);
    }
}
