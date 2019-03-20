package com.fpt.prm.bikeshare.Helper;

import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataFaker {
    public static User getFakeUser() {
        return new User(
            1,
                "Minh",
                "",
                "mih@fancy.galant",
                "F402",
                "0123456789",
                "https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-1/p200x200/45182208_411268876070568_149727619410034688_n.jpg?_nc_cat=1&_nc_oc=AQld9D3uyK-gbZmx-EP5zVomiileP7L1LAdfzm2fKNSfeYkRMNkBsHyeF7MhapTkFYA&_nc_ht=scontent.fhan2-2.fna&oh=92e03a8bf63f3b45029c5152b98fa0df&oe=5D0D10BF",
                1000,
                new Date(),
                new Date()
        );
    }
    public static List getFakeListPost(){
        List<Post> list = new ArrayList<>();
        //TODO get all Post
        list.add(getFakeBikeDetail(1));
        list.add(getFakeBikeDetail(2));

        return list;
    }
    public static Post getFakeBikeDetail(int id) {
        return new Post(
                id,
                1,
                "Xe Air Blade moi",
                "http://phutungxemayvn.com/uploadsys/phu-tung-chinh-hieu/phutungxemay/phu-tung-air-blade/catalogue/phu-tung-xe-ab.jpg",
                "Do xang sau khi thue",
                1000,
                new Date(),
                new Date()
        );
    }
}
