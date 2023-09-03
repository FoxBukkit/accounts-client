package com.mojang.api.profiles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class HttpProfileRepositoryIntegrationTests {

    @Test
    public void findProfilesByNames_existingNameProvided_returnsProfile() throws Exception {
        ProfileRepository repository = new HttpProfileRepository("minecraft");

        Profile[] profiles = repository.findProfilesByNames("mollstam");

        assertThat(profiles.length, is(1));
        assertThat(profiles[0].getName(), is(equalTo("mollstam")));
        assertThat(profiles[0].getId(), is(equalTo("f8cdb6839e9043eea81939f85d9c5d69")));
    }

    @Test
    public void findProfilesByNames_existingMultipleNamesProvided_returnsProfiles() throws Exception {
        ProfileRepository repository = new HttpProfileRepository("minecraft");

        Profile[] profiles = repository.findProfilesByNames("mollstam", "KrisJelbring");

        assertThat(profiles.length, is(2));

        int mollstam_index = -1;
        int krisjelbring_index = -1;
        if (profiles[0].getName().equals("mollstam")) {
            mollstam_index = 0;
            krisjelbring_index = 1;
        } else {
            mollstam_index = 1;
            krisjelbring_index = 0;
        }

        assertThat(profiles[mollstam_index].getName(), is(equalTo("mollstam")));
        assertThat(profiles[mollstam_index].getId(), is(equalTo("f8cdb6839e9043eea81939f85d9c5d69")));
        assertThat(profiles[krisjelbring_index].getName(), is(equalTo("KrisJelbring")));
        assertThat(profiles[krisjelbring_index].getId(), is(equalTo("7125ba8b1c864508b92bb5c042ccfe2b")));
    }

    @Test
    public void findProfilesByNames_nonExistingNameProvided_returnsEmptyArray() throws Exception {
        ProfileRepository repository = new HttpProfileRepository("minecraft");

        Profile[] profiles = repository.findProfilesByNames("doesnotexist$*not even legal");

        assertThat(profiles.length, is(0));
    }
}
