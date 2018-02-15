package com.frank.lms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonItems {

    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ItemsBean {
        /**
         * volumeInfo : {"title":"悪人列伝","subtitle":"近世篇","authors":["海音寺潮五郎"],"publishedDate":"2007-01",
         * "description":"この巻に登場するのは、日野富子、松永久秀、陶晴賢、宇喜多直家、松平忠直、徳川綱吉の六人。いずれ劣らぬ\u201c悪人\u201d
         * たちだが、晴賢、久秀のように悪逆無道の限りを尽くした武将もいれば、綱吉のように賢く気性もすぐれていながら、五代将軍となったが故に、後世の誹りを受けることになった人もいるのである。天下納得の六悪人参上。",
         * "industryIdentifiers":[{"type":"ISBN_10","identifier":"4167135507"},{"type":"ISBN_13",
         * "identifier":"9784167135508"}],"pageCount":285,"imageLinks":{"smallThumbnail":"http://books.google
         * .com/books/content?id=BAv9PwAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api",
         * "thumbnail":"http://books.google.com/books/content?id=BAv9PwAACAAJ&printsec=frontcover&img=1&zoom=1&source
         * =gbs_api"}}
         */

        private VolumeInfoBean volumeInfo;

        public VolumeInfoBean getVolumeInfo() {
            return volumeInfo;
        }

        public void setVolumeInfo(VolumeInfoBean volumeInfo) {
            this.volumeInfo = volumeInfo;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class VolumeInfoBean {
            /**
             * title : 悪人列伝
             * subtitle : 近世篇
             * authors : ["海音寺潮五郎"]
             * publishedDate : 2007-01
             * description :
             * この巻に登場するのは、日野富子、松永久秀、陶晴賢、宇喜多直家、松平忠直、徳川綱吉の六人。いずれ劣らぬ“悪人”たちだが、晴賢、久秀のように悪逆無道の限りを尽くした武将もいれば、綱吉のように賢く気性もすぐれていながら、五代将軍となったが故に、後世の誹りを受けることになった人もいるのである。天下納得の六悪人参上。
             * industryIdentifiers : [{"type":"ISBN_10","identifier":"4167135507"},{"type":"ISBN_13",
             * "identifier":"9784167135508"}]
             * pageCount : 285
             * imageLinks : {"smallThumbnail":"http://books.google
             * .com/books/content?id=BAv9PwAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api",
             * "thumbnail":"http://books.google
             * .com/books/content?id=BAv9PwAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"}
             */

            private String title;
            private String subtitle;
            private String publishedDate;
            private String description;
            private int pageCount;
            private ImageLinksBean imageLinks;
            private List<String> authors;
            private List<IndustryIdentifiersBean> industryIdentifiers;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getPublishedDate() {
                return publishedDate;
            }

            public void setPublishedDate(String publishedDate) {


                this.publishedDate = publishedDate;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

            public ImageLinksBean getImageLinks() {
                return imageLinks;
            }

            public void setImageLinks(ImageLinksBean imageLinks) {
                this.imageLinks = imageLinks;
            }

            public List<String> getAuthors() {
                return authors;
            }

            public void setAuthors(List<String> authors) {
                this.authors = authors;
            }

            public List<IndustryIdentifiersBean> getIndustryIdentifiers() {
                return industryIdentifiers;
            }

            public void setIndustryIdentifiers(List<IndustryIdentifiersBean> industryIdentifiers) {
                this.industryIdentifiers = industryIdentifiers;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class ImageLinksBean {
                /**
                 * smallThumbnail : http://books.google
                 * .com/books/content?id=BAv9PwAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api
                 * thumbnail : http://books.google
                 * .com/books/content?id=BAv9PwAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api
                 */

                private String smallThumbnail;
                private String thumbnail;

                public String getSmallThumbnail() {
                    return smallThumbnail;
                }

                public void setSmallThumbnail(String smallThumbnail) {
                    this.smallThumbnail = smallThumbnail;
                }

                public String getThumbnail() {
                    return thumbnail;
                }

                public void setThumbnail(String thumbnail) {
                    this.thumbnail = thumbnail;
                }
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class IndustryIdentifiersBean {
                /**
                 * type : ISBN_10
                 * identifier : 4167135507
                 */

                private String type;
                private String identifier;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getIdentifier() {
                    return identifier;
                }

                public void setIdentifier(String identifier) {
                    this.identifier = identifier;
                }
            }
        }
    }
}
