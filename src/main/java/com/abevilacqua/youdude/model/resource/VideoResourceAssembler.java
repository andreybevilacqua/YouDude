package com.abevilacqua.youdude.model.resource;

import com.abevilacqua.youdude.controller.hateoas.VideoController_Level3;
import com.abevilacqua.youdude.model.Video;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class VideoResourceAssembler extends RepresentationModelAssemblerSupport<Video, VideoResource> {

  public VideoResourceAssembler() {
    super(VideoController_Level3.class, VideoResource.class);
  }

  @Override // You need to override this instantiateModel() method to be able to set the correct constructor.
  protected VideoResource instantiateModel(Video video) {
    return new VideoResource(video);
  }

  @Override
  public VideoResource toModel(Video video) {
    return createModelWithId(video.getId(), video);
  }

  @Override
  public CollectionModel<VideoResource> toCollectionModel(Iterable<? extends Video> entities) {
    return super.toCollectionModel(entities);
  }
}
