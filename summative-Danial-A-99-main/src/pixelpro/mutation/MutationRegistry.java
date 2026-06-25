package pixelpro.mutation;

import java.util.HashMap;
import java.util.Map;

import pixelpro.mutation.effect.BlurMutation;
import pixelpro.mutation.effect.BulgeMutation;
import pixelpro.mutation.effect.EdgeMutation;
import pixelpro.mutation.effect.EmbossMutation;
import pixelpro.mutation.effect.NeonMutation;
import pixelpro.mutation.effect.OilMutation;
import pixelpro.mutation.effect.PixelationMutation;
import pixelpro.mutation.effect.SwirlMutation;
import pixelpro.mutation.effect.WaveMutation;
import pixelpro.mutation.effect.WireMutation;
import pixelpro.mutation.filter.BrightnessMutation;
import pixelpro.mutation.filter.CinematicMutation;
import pixelpro.mutation.filter.ComicMutation;
import pixelpro.mutation.filter.GrayscaleMutation;
import pixelpro.mutation.filter.HeatMutation;
import pixelpro.mutation.filter.InvertMutation;
import pixelpro.mutation.filter.NightMutation;
import pixelpro.mutation.filter.SepiaMutation;
import pixelpro.mutation.transform.MirrorXMutation;
import pixelpro.mutation.transform.MirrorYMutation;
import pixelpro.mutation.transform.RotateXMutation;
import pixelpro.mutation.transform.RotateYMutation;
import pixelpro.mutation.transform.ShearXMutation;
import pixelpro.mutation.transform.ShearYMutation;
import pixelpro.mutation.transform.ZoomInMutation;
import pixelpro.mutation.transform.ZoomOutMutation;

public class MutationRegistry {
    private final Map<String, Mutation> registry = new HashMap<>();

    public MutationRegistry() {
        registerTransforms();
        registerFilters();
        registerEffects();
    }

    private void registerTransforms() {
        registry.put("mirror-x", new MirrorXMutation());
        registry.put("mirror-y", new MirrorYMutation());
        registry.put("rotate-left", new RotateXMutation());
        registry.put("rotate-right", new RotateYMutation());
        registry.put("shear-x", new ShearXMutation());
        registry.put("shear-y", new ShearYMutation());
        registry.put("zoom-in", new ZoomInMutation());
        registry.put("zoom-out", new ZoomOutMutation());
    }

    private void registerFilters() {
        registry.put("brightness", new BrightnessMutation());
        registry.put("cinematic", new CinematicMutation());
        registry.put("comic", new ComicMutation());
        registry.put("grayscale", new GrayscaleMutation());
        registry.put("heat", new HeatMutation());
        registry.put("invert", new InvertMutation());
        registry.put("night", new NightMutation());
        registry.put("sepia", new SepiaMutation());
    }

    private void registerEffects() {
        registry.put("blur", new BlurMutation());
        registry.put("bulge", new BulgeMutation());
        registry.put("edge", new EdgeMutation());
        registry.put("emboss", new EmbossMutation());
        registry.put("neon", new NeonMutation());
        registry.put("oil", new OilMutation());
        registry.put("pixel", new PixelationMutation());
        registry.put("swirl", new SwirlMutation());
        registry.put("wave", new WaveMutation());
        registry.put("wire", new WireMutation());
    }

    public Mutation getFilter(String key) {
        if (key == null) {
            return null;
        }

        return registry.get(key.toLowerCase().trim());
    }
}
