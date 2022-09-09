package com.example.akka.sharding.kv.spring.api;

import com.example.akka.sharding.kv.actor.KvService;

import com.example.akka.sharding.kv.spring.model.ResponseStored;
import com.example.akka.sharding.kv.spring.model.StoreKv;
import com.example.akka.sharding.kv.spring.model.StoredValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Controller
public class DbApiController implements DbApi {

    private static final Logger log = LoggerFactory.getLogger(DbApiController.class);
    private final NativeWebRequest request;
    private final KvService kvService;

    @Autowired
    public DbApiController(NativeWebRequest request, KvService kvService) {
        this.request = request;
        this.kvService = kvService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<StoredValue> getStoredValue(String key) {
        String value = null;
        try {
            value = kvService.getValue(key).toCompletableFuture().get();
        } catch (Exception ex) {
            log.error(String.format("Exception at getStoredValue: %s", ex.toString()));
        }
        StoredValue res = new StoredValue().value(value);
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity<ResponseStored> storeValue(String key, String value){
        Boolean stored = false;
        try {
            stored = kvService.putKeyValue(key, value).toCompletableFuture().get();
        } catch (Exception ex) {
            log.error(String.format("Exception at storeValue: %s", ex.toString()));
            ex.printStackTrace();
        }
        ResponseStored res = new ResponseStored().stored(stored);
        return ResponseEntity.ok(res);
    }
}
