package com.imz.imz_example_mod.capability;

import com.imz.imz_example_mod.Tag;

public interface ITag {
    public Tag[] getTags();
    public void setTags(Tag[] tags);
    public void addTag(Tag tag);
}
