package com.kenshiro.concurrent.jvm;

import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GraphImage {

    private List<GraphImages> GraphImages;

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        GraphImage g = gson.fromJson(new FileReader("C:/Users/Kenshiro/Desktop/zinhyo1004.json"),
                GraphImage.class);

        List<String> list = new ArrayList<>();
        for (GraphImage.GraphImages graphImage : g.getGraphImages()) {
            for (String url : graphImage.getUrls()) {
                list.add(url);
            }
        }

        System.out.println(list.size());
    }

    @NoArgsConstructor
    @Data
    public static class GraphImages {
        private String __typename;
        private Boolean comments_disabled;
        private Dimensions dimensions;
        private String display_url;
        private EdgeMediaPreviewLike edge_media_preview_like;
        private EdgeMediaToCaption edge_media_to_caption;
        private EdgeMediaToComment edge_media_to_comment;
        private Object gating_info;
        private String id;
        private Boolean is_video;
        private Object media_preview;
        private Owner owner;
        private String shortcode;
        private List<?> tags;
        private Integer taken_at_timestamp;
        private List<ThumbnailResources> thumbnail_resources;
        private String thumbnail_src;
        private List<String> urls;
        private String username;
        private Integer video_view_count;

        @NoArgsConstructor
        @Data
        public static class Dimensions {
            private Integer height;
            private Integer width;
        }

        @NoArgsConstructor
        @Data
        public static class EdgeMediaPreviewLike {
            private Integer count;
        }

        @NoArgsConstructor
        @Data
        public static class EdgeMediaToCaption {
            private List<Edges> edges;

            @NoArgsConstructor
            @Data
            public static class Edges {
                private Node node;

                @NoArgsConstructor
                @Data
                public static class Node {
                    private String text;
                }
            }
        }

        @NoArgsConstructor
        @Data
        public static class EdgeMediaToComment {
            private Integer count;
        }

        @NoArgsConstructor
        @Data
        public static class Owner {
            private String id;
        }

        @NoArgsConstructor
        @Data
        public static class ThumbnailResources {
            private Integer config_height;
            private Integer config_width;
            private String src;
        }
    }
}
